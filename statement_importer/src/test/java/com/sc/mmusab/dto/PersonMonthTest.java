package com.sc.mmusab.dto;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PersonMonthTest {

  @Test
  void testObjectOverrideMethods() {
    PersonMonth pm1 = new PersonMonth("a", 1, 1);
    PersonMonth pm2 = new PersonMonth("a", 1, 1);
    assertEquals(pm1.hashCode(), pm2.hashCode());
    assertEquals(pm1.toString(), pm2.toString());
    assertTrue(pm1.equals(pm2));
    assertEquals(pm1, pm2);
  }

  @Test
  void testMap() {
    PersonMonth pm1 = new PersonMonth("a", 1, 1);
    PersonMonth pm2 = new PersonMonth("a", 1, 1);
    Map<PersonMonth, String> map = new HashMap<>();
    map.put(pm1, "1");
    map.put(pm2, "2");
    assertEquals(1, map.size());
    assertEquals("2", map.get(new PersonMonth("a", 1, 1)));
  }
}