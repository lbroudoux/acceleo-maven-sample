package com.github.lbroudoux.myapp.domain;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.junit.Test;
/**
 * 
 * @author laurent
 */
public class StuffTest{

   @Test
   public void testStuff(){
      
      try{
         // Test field presence.
         Field foo = Stuff.class.getDeclaredField("foo");
         assertNotNull("Stuff class should have a foo field", foo);
         Field bar = Stuff.class.getDeclaredField("bar");
         assertNotNull("Stuff class should have a bar field", bar);
      }
      catch (Exception e){
         fail("One or more field is missing into Stuf class");
      }
      
      // Test annotation presence.
      Annotation annotation = Stuff.class.getAnnotation(javax.persistence.Entity.class);
      assertNotNull("Stuff class should have an Entity annotation", annotation);
   }

}
