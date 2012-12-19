package com.github.lbroudoux.acceleo.uml.java.jpa.files;

import static org.junit.Assert.*;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ModifierSet;

import java.io.FileInputStream;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.lbroudoux.japa.ClassSummary;
import com.github.lbroudoux.japa.ClassSummaryVisitor;
/**
 * This is an Acceleo integration test that checks the entityFile acceleo template results.
 * @author laurent
 */
public class JapaEntityFileTest{

   /** The compilation unit under test. */
   private static CompilationUnit stuffCu = null;
   /** The summary of the Java class being checked. */
   private static ClassSummary cs = null;
   
   
   @BeforeClass
   public static void beforeClass() throws Exception{
      // Creates an input stream for the file to be parsed.
      //FileInputStream in = new FileInputStream("/home/laurent/dev/github/acceleo-maven-sample/com.github.lbroudoux.myapp/src/main/java/com/github/lbroudoux/myapp/domain/Stuff.java");
      FileInputStream in = new FileInputStream("target/test-resources/main/java/com/github/lbroudoux/acceleo/uml/java/jpa/domain/Stuff.java");
      
      try{
          // Parse the file.
         stuffCu = JavaParser.parse(in);
      }
      finally{
          in.close();
      }
      
      // Use a visitor to build a summary.
      ClassSummaryVisitor csVisitor = new ClassSummaryVisitor();
      csVisitor.visit(stuffCu, null);
      cs = csVisitor.getSummary();
   }
   
   @Test
   public void testClassDeclaration(){
      assertFalse(cs.getClassDeclaration().isInterface());
      assertEquals(ModifierSet.PUBLIC, cs.getClassDeclaration().getModifiers());
   }
   
   @Test
   public void testAnnotationPresence(){
      // Check that JPA annotation is present.
      assertTrue(cs.hasAnnotation("javax.persistence.Entity"));
   }
   
   @Test
   public void testFieldsPresence(){
      // Check that two fields have been produced.
      assertTrue(cs.hasField("foo"));
      assertEquals(ModifierSet.PUBLIC, cs.getField("foo").getModifiers());
      assertTrue(cs.hasField("bar"));
      assertEquals(ModifierSet.PUBLIC, cs.getField("bar").getModifiers());
   }
   
   @Test
   public void testFieldsJavadoc(){
      assertTrue(cs.getField("bar").getJavaDoc().toString().contains("Description of the property bar."));
   }
}
