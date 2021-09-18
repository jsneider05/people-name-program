package com.code.challenge.model;

public class Constant {

  public static final Integer LIMIT_MODIFIED_NAME = 25;
  public static final Integer LIMIT_TOP = 10;

  public static final String FORMAT_CARDINALITY =
      "1. The names' cardinality for full, last, and first names:\n"
          + "    Full names : %s\n"
          + "    Last names: %s\n"
          + "    First names: %s\n";
  public static final String FORMAT_TOP_TEN_LAST_NAMES = "2. The 10 most common last names are:\n"
      + "    %s\n";
  public static final String FORMAT_TOP_TEN_FIRST_NAMES = "3. The 10 most common first names are:\n"
      + "    %s\n";
  public static final String MESSAGE_MODIFIED_ORIGINAL_LIST = "4. List of Modified Names\n"
      + "    4.1 Original list names:\n";
  public static final String FORMAT_NAME = "        %s\n";
  public static final String MESSAGE_MODIFIED_LIST_NAMES = "    4.2 Modified list names:";

  public static final String FILE_NAME_INPUT = "data/coding-test-data.txt";
  public static final String FILE_NAME_OUTPUT = "./output.txt";

}
