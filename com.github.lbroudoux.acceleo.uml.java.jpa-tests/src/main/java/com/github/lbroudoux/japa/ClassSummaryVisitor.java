package com.github.lbroudoux.japa;

import japa.parser.ast.BlockComment;
import japa.parser.ast.Comment;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.LineComment;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.MarkerAnnotationExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;
/**
 * This is a basic Visitor implementation for http://code.google.com/p/javaparser
 * visitor that just fills up a ClassSummary. Just call visist() and then retrieve
 * the summary later.
 * @author laurent
 */
public class ClassSummaryVisitor extends VoidVisitorAdapter<Object>{

   /** Wrapped class summary model fed by this visitor. */ 
   private ClassSummary summary = null;
   
   /**
    * Get the wrapped summary fed by this visitor
    * @return The wrapped model representing visited class summary
    */
   public ClassSummary getSummary(){
      return summary;
   }
   
   
   // Implementation of VoidVisitorAdapter -------------------------------------
   
   @Override
   public void visit(CompilationUnit n, Object arg){
      for (Comment c : n.getComments()){
         c.accept(this, arg);
      }
      super.visit(n, arg);
   }
   
   @Override
   public void visit(ClassOrInterfaceDeclaration n, Object arg){
      summary = new ClassSummary(n);
      super.visit(n, arg);
   }
   
   @Override
   public void visit(MarkerAnnotationExpr n, Object arg){
      summary.getAnnotations().put(n.getName().toString(), n);
   }
   
   @Override
   public void visit(FieldDeclaration n, Object arg){
      summary.getFields().put(n.getVariables().get(0).getId().getName(), n);
   }
   
   @Override
   public void visit(MethodDeclaration n, Object arg){
      summary.getMethods().add(n);
      super.visit(n, arg);
   }
   
   @Override
   public void visit(LineComment n, Object arg){
      System.err.println("//" + n.getContent());
   }

   @Override
   public void visit(BlockComment n, Object arg){
      System.err.println("/* " + n.getContent());
   }
}
