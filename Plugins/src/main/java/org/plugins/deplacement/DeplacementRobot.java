package org.plugins.deplacement;

import java.util.List;
import org.core.*;
import org.plugins.*;


/**
 * cette classe permet de gerer le plugin
 * de deplacement "directionRobot", soit chaque robot
 * se deplace vers une direction donn�e
 */

@Plugin(name = "DeplacementRobot", type = PluginType.DEPLACEMENT)
public class DeplacementRobot {


	@Deplacement(name="deplacer")
	public void deplacement(Robot deplacement, List<Robot> l)
	{
		int directionCourante =0;

		for (Robot robot:l)
		{
			if (robot.getDirectionCourante() !=0 )
			{
				directionCourante = directionCourante -1;
				deplacement.setDirectionCourante(directionCourante);

			}
		}
	}
	
}
