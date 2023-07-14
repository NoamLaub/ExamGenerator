package module;

import view.Menu;

public class CommandCase9 implements myCommand {
	OperatingSystemModule module;
	Menu menu;
	
	public CommandCase9 (OperatingSystemModule module,Menu menu)
	{
		this.module=module;
		this.menu=menu;
	}
	@Override
	public void execute() {
		module.transferTestsToTreeSet();
    	menu.setCase10Disable(false);
	}

}
