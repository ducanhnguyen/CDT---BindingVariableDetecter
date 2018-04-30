package helloworld.handlers;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IBinding;
import org.eclipse.cdt.core.dom.ast.IType;
import org.eclipse.cdt.core.dom.ast.IVariable;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICModel;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.ISourceRoot;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

/**
 * Display all bindings of variables
 * 
 * @author Duc Anh Nguyen
 *
 */
public class BindingDiscovery {

	public BindingDiscovery(String projectName) throws CoreException, InterruptedException {
		ICModel model = CoreModel.getDefault().getCModel();
		ICProject cproject = model.getCProject(projectName);

		if (cproject != null) {
			System.out.println("Found a project named " + projectName);
			IIndex index = CCorePlugin.getIndexManager().getIndex(cproject);
			index.acquireReadLock(); // we need a read-lock on the index

			try {
				ISourceRoot sourceRoot = (ISourceRoot) cproject.getChildren()[0];
				ITranslationUnit[] units = sourceRoot.getTranslationUnits();

				for (ITranslationUnit unit : units) {
					System.out.println("* Parse " + unit.getElementName());
					IASTTranslationUnit astUnit = unit.getAST();

					ASTVisitor visitor = new ASTVisitor() {

						@Override
						public int visit(IASTName name) {
							IBinding binding = name.resolveBinding();

							if (binding instanceof IVariable) {
								IVariable var = (IVariable) binding;
								IType type = var.getType();
								System.out.println("Name = " + name.getRawSignature() + " [binding] type=" + type + " ("
										+ type.getClass() + ")");
							}

							return super.visit(name);
						}
					};
					visitor.shouldVisitNames = true;
					astUnit.accept(visitor);
				}
			} finally {
				index.releaseReadLock();
			}

		} else
			System.out.println("Do not found project");

	}

}
