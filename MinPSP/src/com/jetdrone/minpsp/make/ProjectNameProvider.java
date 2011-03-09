package com.jetdrone.minpsp.make;

import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.cdt.managedbuilder.core.IManagedOutputNameProvider;
import org.eclipse.cdt.managedbuilder.core.ITool;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class ProjectNameProvider implements IManagedOutputNameProvider {

	public IPath[] getOutputNames(ITool tool, IPath[] primaryInputNames) {
		IPath pathes[] = new Path[1];
		String artifactName = "default";

		try {
			IConfiguration configuration = (IConfiguration) ((IToolChain) tool.getParent()).getParent();
			artifactName = configuration.getArtifactName();
		} catch (ClassCastException e) {
		} catch (NullPointerException e) {
		}

		String[] ouputExt = tool.getAllOutputExtensions();
		pathes[0] = new Path(artifactName + "." + ouputExt[0]);
		return pathes;
	}

}
