package com.johnkuper.mappers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.annotations.ClassAnnotation;
import com.johnkuper.annotations.FieldAnnotation;
import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.dataformapping.CreditCard;
import com.johnkuper.exceptions.OwnMapperException;

public class OwnMapper {

	final static Logger logger = LoggerFactory.getLogger(OwnMapper.class);

	public <T extends Object, Y extends Object> void getFieldsAndClasses(
			T input, Y output) throws OwnMapperException {

		Class<?> fromClass = input.getClass();
		logger.debug("Getting fields from source class");
		Field[] fromFields = fromClass.getDeclaredFields();

		Class<?> toClass = output.getClass();
		logger.debug("Getting fields from destination class");
		Field[] toFields = toClass.getDeclaredFields();

		if (verificationClassForMapping(fromClass, toClass)) {
			logger.debug("Verification of classes is successfully. Start mapProcessing");
			mapProcessing(input, output, fromClass, toClass, fromFields,
					toFields);
		}
	}

	private void mapProcessing(Object input, Object output, Class<?> fromClass,
			Class<?> toClass, Field[] fromFields, Field[] toFields)
			throws OwnMapperException {

		if (fromFields != null && toFields != null) {
			logger.debug("Both classes have fields. Trying to read and put information");
			for (Field fromField : fromFields) {
				try {
					Annotation[] annotations = fromField
							.getDeclaredAnnotations();
					for (Annotation annotation : annotations) {
						if (annotation instanceof FieldAnnotation) {
							FieldAnnotation fieldAnnotation = (FieldAnnotation) annotation;
							String annotationName = fieldAnnotation.name();
							Field toField = toClass
									.getDeclaredField(annotationName);
							if (fromField.getType().equals(toField.getType())) {
								if (!fromField.isAccessible()) {
									logger.debug(
											"Field '{}' is not accessible. Trying to get value from getters and set it to output class",
											fromField.getName());
									String fromFieldName = fromField.getName();

									Object value = getFieldValueFromObject(
											input, fromFieldName);

									toField.setAccessible(true);
									logger.debug(
											"Put field value from getter into output class = {}",
											value);
									toField.set(output, value);
								} else {
									logger.debug("Field is accessible. Get value from it and set to output class");
									Object value = fromField.get(input);
									toField.setAccessible(true);
									toField.set(output, value);
								}
							}

						}
					}
				} catch (SecurityException e) {
					String msg = e.toString();
					logger.error(msg);
					e.printStackTrace();
					throw new OwnMapperException(msg);
				} catch (NoSuchFieldException e) {
					String msg = e.toString();
					logger.error(msg);
					e.printStackTrace();
					throw new OwnMapperException(msg);
				} catch (IllegalArgumentException e) {
					String msg = e.toString();
					logger.error(msg);
					e.printStackTrace();
					throw new OwnMapperException(msg);
				} catch (IllegalAccessException e) {
					String msg = e.toString();
					logger.error(msg);
					e.printStackTrace();
					throw new OwnMapperException(msg);
				}
			}

		}

	}

	private boolean verificationClassForMapping(Class<?> input, Class<?> output)
			throws OwnMapperException {
		logger.debug("Trying to compare annotation name and output class name");
		if (input.isAnnotationPresent(ClassAnnotation.class)) {
			Annotation annotation = input.getAnnotation(ClassAnnotation.class);
			ClassAnnotation classAnotation = (ClassAnnotation) annotation;
			String annName = classAnotation.name();
			String toClassName = output.getSimpleName();
			if (toClassName.equals(annName)) {
				logger.debug(
						"Annotation name = {}, output class name = {} and they are equals",
						annName, toClassName);
				return true;
			} else {
				logger.debug(
						"Annotation name = {}, output class name = {} and they are not equals",
						annName, toClassName);
				String msg = "@ClassAnnotation name and output class name not equal.";
				throw new OwnMapperException(msg);
			}
		}

		return false;

	}

	public void outputDestiData(BillingDetails bildetails) {
		logger.debug("Summary output class data");
		Class<?> bilclazz = bildetails.getClass();
		String bilClassName = bilclazz.getSimpleName();
		logger.debug(bilClassName + " CC_number = " + bildetails.getCC_number());
		logger.debug(bilClassName + " CC_cardType = "
				+ bildetails.getCC_cardType());
		logger.debug(bilClassName + " CC_exp_month = "
				+ bildetails.getCC_exp_month());
		logger.debug(bilClassName + " CC_exp_year = "
				+ bildetails.getCC_exp_year());
		logger.debug("================================");
	}

	public void outputSourceData(CreditCard creditcard) {
		logger.debug("================================");
		logger.debug("Summary source class data");
		Class<?> creditclazz = creditcard.getClass();
		String creditClassName = creditclazz.getSimpleName();
		logger.debug(creditClassName + " number " + creditcard.getNumber());
		logger.debug(creditClassName + " cardType " + creditcard.getCardType());
		logger.debug(creditClassName + " expMonth " + creditcard.getExpMonth());
		logger.debug(creditClassName + " expYear " + creditcard.getExpYear());
		logger.debug("================================");
	}

	private Object getFieldValueFromObject(Object object, String fieldName)
			throws OwnMapperException {

		Class<?> clazz = object != null ? object.getClass() : null;
		if (clazz == null) {
			return null;
		}
		String getterFieldName = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		String getterName = "get" + getterFieldName;
		try {
			Method method = clazz.getMethod(getterName);
			Object valueObject = method.invoke(object, (Object[]) null);
			return valueObject != null ? valueObject : "";

		} catch (Exception e) {
			String msg = "Error during getting value from getters";
			e.printStackTrace();
			logger.error(msg);
			throw new OwnMapperException(msg);
		}

	}

}
