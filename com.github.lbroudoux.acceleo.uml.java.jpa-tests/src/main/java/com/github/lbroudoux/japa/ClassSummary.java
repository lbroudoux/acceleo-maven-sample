package com.github.lbroudoux.japa;

import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.type.ClassOrInterfaceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This is a thin model wrapper around informations provided by http://code.google.com/p/javaparser
 * during the parsing / visit of a class definition. This wrapper is intended to be created by
 * ClassSummaryVisitor and then utilities method later used for checking class file source properties. 
 * @author laurent
 */
public class ClassSummary{

   /** The inner root class declaration. */
   private ClassOrInterfaceDeclaration clazzDeclaration = null;
   
   /** */
   private Map<String, AnnotationExpr> annotations = new HashMap<String, AnnotationExpr>();
   
   /** */
   private Map<String, FieldDeclaration> fields = new HashMap<String, FieldDeclaration>();
   
   /** */
   private List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
   
   
   // Constructors -------------------------------------------------------------
   
   /**
    * Build a new summary from a class or interface declaration.
    * @param clazzDeclaration The inner root declaration
    */
   protected ClassSummary(ClassOrInterfaceDeclaration clazzDeclaration){
      this.clazzDeclaration = clazzDeclaration;
   }
   
   
   // Public -------------------------------------------------------------------
   
   /**
    * 
    * @param typeName
    * @return
    */
   public boolean hasExtends(String typeName){
      List<ClassOrInterfaceType> types = clazzDeclaration.getExtends();
      for (ClassOrInterfaceType type : types){
         if (typeName.equals(type.getName())){
            return true;
         }
      }
      return false;
   }
   
   /** */
   public ClassOrInterfaceDeclaration getClassDeclaration(){
      return clazzDeclaration;
   }
   
   /**
    * 
    */
   public boolean hasAnnotation(String name){
      return annotations.containsKey(name);
   }
   
   /** */
   public Map<String, AnnotationExpr> getAnnotations(){
      return annotations;
   }
   
   /**
    * Check if represented class has a field called "name".
    * @param name Name of field to check presence
    * @return if a field exists, false otherwise
    */
   public boolean hasField(String name){
      return fields.containsKey(name);
   }
   
   /**
    * 
    * @param name
    * @return
    */
   public FieldDeclaration getField(String name){
      return fields.get(name);
   }
   
   /** */
   public Map<String, FieldDeclaration> getFields(){
      return fields;
   }
   
   /** */
   public List<MethodDeclaration> getMethods(){
      return methods;
   }
}
