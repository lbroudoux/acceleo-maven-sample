package com.github.lbroudoux.acceleo.uml.java.jpa.files;

import static org.junit.Assert.*;

import japa.parser.ast.body.ModifierSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.lbroudoux.jdt.ClassSummary;
import com.github.lbroudoux.jdt.ClassSummaryVisitor;
/**
 * 
 * @author laurent
 */
public class JDTEntityFileTest{

   /** The compilation unit under test. */
   private static CompilationUnit stuffCu = null;
   /** The summary of the Java class being checked. */
   private static ClassSummary cs = null;
   
   
   @BeforeClass
   public static void beforeClass() throws Exception{
      // Creates an input stream for the file to be parsed.
      File javaFile = new File("target/test-resources/main/java/com/github/lbroudoux/acceleo/uml/java/jpa/domain/Stuff.java");
      BufferedReader in = new BufferedReader(new FileReader(javaFile));
      final StringBuffer buffer = new StringBuffer();
      String line = null;
      while (null != (line = in.readLine())) {
         buffer.append(line).append("\n");
      }
      
      // Parse and get the compilation unit.
      ASTParser parser = ASTParser.newParser(AST.JLS3);
      parser.setKind(ASTParser.K_COMPILATION_UNIT);
      parser.setSource(buffer.toString().toCharArray());
      parser.setResolveBindings(false);
      stuffCu = (CompilationUnit)parser.createAST(null);
      
      ClassSummaryVisitor csVisitor = new ClassSummaryVisitor();
      stuffCu.accept(csVisitor);
      List<Comment> comments = stuffCu.getCommentList();
      for (Comment c : comments){
         c.accept(csVisitor);
      }
      cs = csVisitor.getSummary();
   }
   
   @Test
   public void testClassDeclaration(){
      assertFalse(cs.getTypeDeclaration().isInterface());
      assertEquals(ModifierSet.PUBLIC, cs.getTypeDeclaration().getModifiers());
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
      assertTrue(cs.getField("foo").getJavadoc().toString().contains("Description of the property foo."));
   }
}
