# CDT---BindingVariableDetecter
This project is a part of Eclipse CDT tutorial. 

## Set up (Window)
1. Download Eclipse for RCP and RAP Developers (for developing plugin)
2. In Eclipse, Install CDT as follows: Help > Install New Software > Input link: "http://download.eclipse.org/tools/cdt/releases/9.4" > ... > Restart

Now Eclipse for developing plugins using CDT has already installed.

## How to run sample
1. Import the sample to Eclipse
2. Right click to the sample > Run as > Eclipse application. Result: New eclipse for testing plugin is displayed.
3. Create a C/C++ project named "cproject" on the new Eclipse. Let's add several source code files to this project.
4. On the new Eclipse, see the menubar > Sample Menu > Sample Command > OK.
5. See the console on the original Eclipse. Binding information is displayed here.

## Example
## Input
// Project cproject

// main.c

#include "main.h"

float c = a;

int main() {

return 0;

}

// main.h

#ifndef MAIN_H_

#define MAIN_H_

int a = 0;

int b = 0;

#endif /* MAIN_H_ */

### Output (on Console)
Found a project named cproject

Parse main.c

Name = a [binding] type=int (class org.eclipse.cdt.internal.core.dom.parser.c.CBasicType)

Name = b [binding] type=int (class org.eclipse.cdt.internal.core.dom.parser.c.CBasicType)

Name = c [binding] type=float (class org.eclipse.cdt.internal.core.dom.parser.c.CBasicType)

Name = a [binding] type=int (class org.eclipse.cdt.internal.core.dom.parser.c.CBasicType)

Parse main.h

Name = a [binding] type=int (class org.eclipse.cdt.internal.core.dom.parser.c.CBasicType)

Name = b [binding] type=int (class org.eclipse.cdt.internal.core.dom.parser.c.CBasicType)
