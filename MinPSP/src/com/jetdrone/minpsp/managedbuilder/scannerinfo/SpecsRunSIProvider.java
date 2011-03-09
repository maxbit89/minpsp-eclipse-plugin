/*******************************************************************************
 * Copyright (c) 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM - Initial API and implementation
 *******************************************************************************/
/* used and Modified by Enrico Ehrich 2008 */

package com.jetdrone.minpsp.managedbuilder.scannerinfo;

import org.eclipse.cdt.make.internal.core.scannerconfig2.GCCSpecsRunSIProvider;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

@SuppressWarnings("restriction")
public class SpecsRunSIProvider  extends GCCSpecsRunSIProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.cdt.make.internal.core.scannerconfig2.GCCSpecsRunSIProvider#initialize()
	 */
	protected boolean initialize() {

		boolean rc = super.initialize();

		if (rc) {
			String pspsdk = java.lang.System.getenv("PSPDEV");
			
			if (Platform.getOS().equals(Platform.OS_WIN32)) {
				// default for windows
				if(pspsdk == null) pspsdk = "c:/pspsdk/bin";
				if( (pspsdk.length() >= 3) && pspsdk.startsWith("/")){
					char volume = pspsdk.charAt(1);
					pspsdk = volume + ":"+ pspsdk.substring(2);
				}
				if(new Path(pspsdk).append(this.fCompileCommand).toFile().exists()) {
					this.fCompileCommand = new Path(pspsdk).append(this.fCompileCommand);
				}
			} else 
			if (Platform.getOS().equals(Platform.OS_LINUX)) {
				// default for linux
				if(pspsdk == null) pspsdk = "/opt/pspsdk/bin";
				if(new Path(pspsdk).append(this.fCompileCommand).toFile().exists()) {
					this.fCompileCommand = new Path(pspsdk).append(this.fCompileCommand);
				}
			} else
			if (Platform.getOS().equals(Platform.OS_MACOSX)) {
				// default for mac
				if(pspsdk == null) pspsdk = "/opt/pspsdk/bin";
				if(new Path(pspsdk).append(this.fCompileCommand).toFile().exists()) {
					this.fCompileCommand = new Path(pspsdk).append(this.fCompileCommand);
				}
			}
		}
		
		return rc;
	}

}
