package com.johnkuper.mappersexecutor;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.dataformapping.CreditCard;
import com.johnkuper.exceptions.OwnMapperException;
import com.johnkuper.mappers.OrikaMapper;
import com.johnkuper.mappers.OwnMapper;

public class MapperExecutor {

	final static Logger logger = LoggerFactory.getLogger(MapperExecutor.class);

	static OwnMapper ownMapper = new OwnMapper();
	static OrikaMapper orikaMapper = new OrikaMapper();

	public static void main(String[] args) {

		CreditCard sourceData = new CreditCard();
		BillingDetails destiData = new BillingDetails();

		int input = 0;

		System.out.println("Please choose a mapper for processing:");
		System.out.println("1. OwnMapper | 2. Orika | 3. Exit");

		while (input != 3) {
			Scanner scanner = new Scanner(System.in);
			input = scanner.nextInt();
			switch (input) {
			case 1:
				try {
					logger.debug("=================");
					logger.debug("Own mapper starts");
					ownMapper.getFieldsAndClasses(sourceData, destiData);
					ownMapper.outputSourceData(sourceData);
					ownMapper.outputDestiData(destiData);
				} catch (OwnMapperException e) {
					logger.error("Program will be terminated, because: "
							+ e.toString());
					e.printStackTrace();
					System.exit(1);
				} finally {
					logger.debug("Own mapper complete");
					scanner.close();
					System.exit(1);
				}
			case 2:
				logger.debug("=================");
				logger.debug("Orika mapper starts");
				orikaMapper.runMapper(CreditCard.class, BillingDetails.class);
				logger.debug("Orika complete");
				System.exit(1);
			case 3:
				logger.info("Thank you and good bye!");
				System.exit(1);
			}
		}

	}

}
