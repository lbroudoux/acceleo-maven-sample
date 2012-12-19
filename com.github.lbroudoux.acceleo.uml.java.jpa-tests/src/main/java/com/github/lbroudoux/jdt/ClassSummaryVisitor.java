package com.github.lbroudoux.jdt;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
/**
 * This is a basic Visitor implementation for Eclipse JDT ASTVisitor
 * that just fills up a ClassSummary. Just call visist() and then retrieve
 * the summary later.
 * @author laurent
 */
public class ClassSummaryVisitor extends ASTVisitor{

   /** Wrapped class summary model fed by this visitor. */ 
   private ClassSummary summary = null;
   
   /**
    * Get the wrapped summary fed by this visitor
    * @return The wrapped model representing visited class summary
    */
   public ClassSummary getSummary(){
      return summary;
   }
   
   // Override of ASTVisitor ---------------------------------------------------
   
   @Override
   public boolean visit(TypeDeclaration node){
      summary = new ClassSummary(node);
      return super.visit(node);
   }
   
   @Override
   public boolean visit(MarkerAnnotation node){
      if (node.getParent().getNodeType() == node.TYPE_DECLARATION){
         // Annotation associated to enclosing class or interface.
         summary.getAnnotations().put(node.getTypeName().toString(), node);
      }
      else if (node.getParent().getNodeType() == node.FIELD_DECLARATION){
         // Annotation associated to field...
         // also returned by FieldDeclaration.modifiers()
      }
      return super.visit(node);
   }
   
   @Override
   public boolean visit(FieldDeclaration node){
      List<VariableDeclarationFragment> vdfs = node.fragments();
      summary.getFields().put(vdfs.get(0).getName().toString(), node);
      /*
      for (VariableDeclarationFragment f : vdfs){
         System.err.println(f.getName());
      }
      */ 
      return super.visit(node);
   }

   @Override
   public boolean visit(LineComment node){
      System.err.println("//" + node.toString());
      return true;
   }

   @Override
   public boolean visit(BlockComment node) {
      System.err.println("/* " + node.toString());
      return super.visit(node);
   }
}
