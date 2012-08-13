package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class TacticsParser {
	public static final String tacticsFile = "..\\..\\tactics.txt";

	private ArrayList buildList = new ArrayList();

	private ArrayList[] colonyBuilds = new ArrayList[9];
	
	private TacticsVisitor visitor = new TacticsVisitor();
	
	public TacticsParser() {
	}

	public void parse() {
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					getClass().getResourceAsStream(tacticsFile)));
			String str;
			while (null != (str = reader.readLine())) {
				if (str.indexOf("//") == 0) {
					continue;
				}
				if (isColonySection(str))
				{
					new ColonySection(str).accept(visitor);
				}
				else if (isLoopSection(str))
				{
					new LoopSection().accept(visitor);
				}
				else if (isFleetCommand(str))
				{
					new FleetCommand(str).accept(visitor);
				}
				else if (isBuildCommand(str))
				{
					new BuildCommand(str).accept(visitor);
				}
				else if (isResearchCommand(str))
				{
					new ResearchCommand(str).accept(visitor);
				}				
				else if (isShipBuildCommand(str))
				{
					new ShipBuildCommand(str).accept(visitor);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class TacticsVisitor {
	Stack<ColonySection> colonySections = new Stack<ColonySection>();

	Stack<LoopSection> loopSections = new Stack<LoopSection>();

	public void startColonySection(ColonySection cs) {
		colonySections.push(cs);
	}

	public void endColonySection(ColonySection cs) {
		ColonySection cs1 = colonySections.peek();
		if (cs1.equals(cs)) {
			colonySections.pop();
		}
	}

	public void startLoop(LoopSection loop) {
		loopSections.push(loop);
	}

	public void endColonySection(LoopSection loop) {
		LoopSection loop1 = loopSections.peek();
		if (loop1.equals(loop)) {
			loopSections.pop();
		}
	}

	public void visitFleetCommand(FleetCommand fc) {

	}

	public void visitBuildCommand(BuildCommand bc) {

	}

	public void visitResearchCommand(ResearchCommand rc) {

	}

	public void visitShipBuildCommand(ShipBuildCommand sbc) {

	}
}
