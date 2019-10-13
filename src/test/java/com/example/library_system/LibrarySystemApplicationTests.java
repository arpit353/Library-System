package com.example.library_system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.library_system.MyTestUnit;;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LibrarySystemApplicationTests {

  @Test
  public void contextLoads() {
  }

  @Test
  public void testMyTestUnit() {

    MyTestUnit myUnit = new MyTestUnit();

    String result = myUnit.concatenate("one", "two");

    assertEquals("onetwo", result);

  }

}
