package com.github.lbroudoux.jdt;

import java.util.Map;
import java.util.HashMap;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
/**
 * This is a thin model wrapper around informations provided by http://help.eclipse.org/indigo/topic/org.eclipse.jdt.doc.isv/reference/api/org/eclipse/jdt/core/dom/AST.html
 * during the parsing / visit of a class definition. This wrapper is intended to be created by
 * ClassSummaryVisitor and then utilities method later used for checking class file source properties.
 * @author laurent
 */
public class ClassSummary{

   /** The inner root class declaration. */
   private TypeDeclaration typeDeclaration = null;
   
   /** */
   private Map<String, Annotation> annotations = new HashMap<String, Annotation>();
   
   /** */
   private Map<String, FieldDeclaration> fields = new HashMap<String, FieldDeclaration>();
   
   
   // Constructors -------------------------------------------------------------
   
   /**
    * Build a new summary from a class or interface declaration.
    * @param typeDeclaration The inner root declaration
    */
   protected ClassSummary(TypeDeclaration typeDeclaration){
      this.typeDeclaration = typeDeclaration;
   }
         
   
   // Public -------------------------------------------------------------------
   
   /** */
   public TypeDeclaration getTypeDeclaration(){
      return typeDeclaration;
   }
   
   /**
    * 
    */
   public boolean hasAnnotation(String name){
      return annotations.containsKey(name);
   }
   
   /** */
   public Map<String, Annotation> getAnnotations(){
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
}
