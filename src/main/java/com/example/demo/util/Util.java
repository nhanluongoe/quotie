package com.example.demo.util;

import java.util.List;
import java.util.stream.Collectors;

public class Util {
  public static List<Character> convertStringToCharList(String str) {
    // Create an empty List of character
    List<Character> chars = str
        // Convert to String to IntStream
        .chars()
        // Convert IntStream to Stream<Character>
        .mapToObj(e -> (char) e)
        // Collect the elements as a List Of Characters
        .collect(Collectors.toList());
    return chars;
  }
}
