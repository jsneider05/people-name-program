package com.code.challenge.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Person {

  private String lastName;
  private String firstName;

  @Override
  public String toString() {
    return lastName + ", " + firstName;
  }
}

