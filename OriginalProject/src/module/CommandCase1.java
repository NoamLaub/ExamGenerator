package module;

import view.Case1;

public class CommandCase1 implements myCommand {
	Case1 case1;
	OperatingSystemModule module;
	
	public CommandCase1 (Case1 c1,OperatingSystemModule module)
	{
		this.case1=c1;
		this.module=module;
	}
	@Override
	public void execute() {
		case1.addTextToWindow(module.toString(module.getArrayOfquestions()));
		case1.showWindow();
	}

}
