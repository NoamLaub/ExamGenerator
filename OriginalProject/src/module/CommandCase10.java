package module;

import view.Menu;

public class CommandCase10 implements myCommand {
	OperatingSystemModule module;
	Menu menu;
	
	public CommandCase10 (OperatingSystemModule module,Menu menu)
	{
		this.module=module;
		this.menu=menu;
	}
	@Override
	public void execute() {
		module.transferTestsToLinkedHashSet();
    	menu.setCase11Disable(false);
	}
}
