package com.relayd.util;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   30.08.2016
 * status   initial
 */
public class CreateEqualMethodForTestClassTest {

	private CreateEqualMethodForTestClass sut = new CreateEqualMethodForTestClass();

	//@formatter:off
	private final static String FIRST_EQUALS = "@Test\r\npublic void testEqualsWithMyself() {\r\n\r\nboolean result = sut.equals(sut);\r\n\r\nassertTrue(result);}";
	private final static String SECOND_EQUALS = "@Test\r\npublic void testEqualsWithNull() {\r\n\r\nboolean result = sut.equals(null);\r\n\r\nassertFalse(result);}";
	private final static String THIRD_EQUALS = "@Test\r\npublic void testEqualsWithNotCompatibleClass() {\r\n\r\nboolean result = sut.equals(new String());\r\n\r\nassertFalse(result);}";
	private final static String FOURTH_EQUALS = "@Test\r\npublic void testEqualsWithShortValueIsNull() {\r\nsut.setShort(null);\r\n\r\nBuyingStockOutUHCCostsPK secondSut = new BuyingStockOutUHCCostsPK();\r\n\r\nsecondSut.setShort((short) 1);\r\n\r\nboolean result = sut.equals(secondSut);\r\n\r\nassertFalse(result);}";
	private final static String FIFTH_EQUALS = "@Test\r\npublic void testEqualsWithBothShortValuesAreNull() {\r\nsut.setShort(null);\r\n\r\nBuyingStockOutUHCCostsPK secondSut = new BuyingStockOutUHCCostsPK();\r\n\r\nsecondSut.setShort(null);\r\n\r\nboolean result = sut.equals(secondSut);\r\n\r\nassertTrue(result);}";
	private final static String SIXTH_EQUALS = "@Test\r\npublic void testEqualsWithShortTwoDiffrentValues() {\r\nsut.setShort((short) 1);\r\n\r\nBuyingStockOutUHCCostsPK secondSut = new BuyingStockOutUHCCostsPK();\r\n\r\nsecondSut.setShort((short) 2);\r\n\r\nboolean result = sut.equals(secondSut);\r\n\r\nassertFalse(result);}";
	private final static String SEVENTH_EQUALS = "@Test\r\npublic void testEqualsWithShortSameValues() {\r\nshort shortValue = (short) 1;\r\n\r\nsut.setShort(shortValue);\r\n\r\nBuyingStockOutUHCCostsPK secondSut = new BuyingStockOutUHCCostsPK();\r\n\r\nsecondSut.setShort(shortValue);\r\n\r\nboolean result = sut.equals(secondSut);\r\n\r\nassertTrue(result);}";
	//@formatter:on

	@Test
	public void testCreateTestEqualsWithMyself() {
		String result = sut.createTestEqualsWithMyself();

		assertEquals("[result] nicht korrekt!", FIRST_EQUALS, result);
	}

	@Test
	public void testCreateTestEqualsWithNull() {
		String result = sut.createTestEqualsWithNull();

		assertEquals("[result] nicht korrekt!", SECOND_EQUALS, result);
	}

	@Test
	public void testCreateTestEqualsWithNotCompatibleClass() {
		String result = sut.createTestEqualsWithNotCompatibleClass();

		assertEquals("[result] nicht korrekt!", THIRD_EQUALS, result);
	}

	@Test
	public void testCreateTestEqualsWithParameterValueIsNull() {
		String classname = "BuyingStockOutUHCCostsPK";
		String attribute = "Short";
		String type = "short";
		String result = sut.createTestEqualsWithParameterValueIsNull(classname, attribute, type);

		assertEquals("[result] nicht korrekt!", FOURTH_EQUALS, result);
	}

	@Test
	public void testCreateTestEqualsWithBothParameterValuesAreNull() {
		String classname = "BuyingStockOutUHCCostsPK";
		String attribute = "Short";
		String result = sut.createTestEqualsWithBothParameterValuesAreNull(classname, attribute);

		assertEquals("[result] nicht korrekt!", FIFTH_EQUALS, result);
	}

	@Test
	public void testCreateTestEqualsWithParameterTwoDiffrentValues() {
		String classname = "BuyingStockOutUHCCostsPK";
		String attribute = "Short";
		String type = "short";

		String result = sut.createTestEqualsWithParameterTwoDiffrentValues(classname, attribute, type);

		assertEquals("[result] nicht korrekt!", SIXTH_EQUALS, result);
	}

	@Test
	public void testCreateTestEqualsWithParameterSameValues() {
		String classname = "BuyingStockOutUHCCostsPK";
		String attribute = "Short";
		String type = "short";

		String result = sut.createTestEqualsWithParameterSameValues(classname, attribute, type);

		assertEquals("[result] nicht korrekt!", SEVENTH_EQUALS, result);

	}

	@Test
	@Ignore
	public void outputForEqual() {
		StringBuilder builder = new StringBuilder();
		builder.append(sut.createTestEqualsWithMyself());
		builder.append(sut.createTestEqualsWithNull());
		builder.append(sut.createTestEqualsWithNotCompatibleClass());

		System.out.println(builder.toString());
	}

	@Test
	public void outputForEqualWithParameter() {
		String classname = "BuyingStockOutUHCCostsPK";
		String attribute = "ShipmentId";
		String type = "int";

		StringBuilder builder = new StringBuilder();
		builder.append(sut.createTestEqualsWithParameterValueIsNull(classname, attribute, type));
		builder.append(sut.createTestEqualsWithBothParameterValuesAreNull(classname, attribute));
		builder.append(sut.createTestEqualsWithParameterTwoDiffrentValues(classname, attribute, type));
		builder.append(sut.createTestEqualsWithParameterSameValues(classname, attribute, type));

		System.out.println(builder.toString());
	}
}