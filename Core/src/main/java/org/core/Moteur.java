package org.core;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Point;


public class Moteur {
    private static final String pluginsAdd = "plugins-additionnels/target/classes";
    private static final String plugins = "Plugins/target/classes";

    // Chargement des classes dans le package du type de plugin specifié
    // TODO : Chercher d'abord dans plugins-additionnels puis compléter avec Plugins
    public Object getClassesPlugins(String typePlugin) {
        // Chargement des classes dans le module Plugins
        Repository repository = new Repository(new File(plugins));
        ArrayList<Class<?>> classes = repository.load();

        // Pour chaque classe chargée, verfier si le package correspond à celui specifié
        Object returnClass = this.getPluginBasique(classes, typePlugin);

        return returnClass;
    }

    public Object getPluginBasique(ArrayList<Class<?>> classes, String typePlugin) {
        for (Class<?> c : classes) {
            try {   
                if(c.getName().matches("org.plugins."+ typePlugin + ".*")) {
                    // Retourne la première class appropriée trouvée
                    return c.getConstructor().newInstance();
                }
            } catch (InstantiationException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IllegalArgumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (NoSuchMethodException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SecurityException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return null;
    }

    public Object getPluginAdditionnel(ArrayList<Class<?>> classes, String typePlugin) {
        for (Class<?> c : classes) {
            try {   
                if(c.getName().matches("org.pluginsadditionnels."+ typePlugin + ".*")) {
                    // Retourne la première class appropriée trouvée
                    return c.getConstructor().newInstance();
                }
            } catch (InstantiationException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IllegalArgumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (NoSuchMethodException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SecurityException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return null;
    }

    // Récupération de toutes les méthodes nécessaires en fonction du name des annotations
    public Method[] getMethodesAttque(Method[] methods) {
        Method[] methodesAttaque = new Method[2];
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if(annotation.toString().matches(".*.name=attaque.*")) {
                    methodesAttaque[0] = method;
                }
                else if(annotation.toString().matches(".*.name=portee.*")) {
                    methodesAttaque[1] = method;
                }
            }
        }
        return methodesAttaque;
    }

    // Récupération de toutes les méthodes nécessaires en fonction du name des annotations
    public Method[] getMethodesDeplacement(Method[] methods) {
        Method[] methodesDeplacement = new Method[1];
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if(annotation.toString().matches(".*.name=deplacer.*")) {
                    methodesDeplacement[0] = method;
                }
            }
        }
        return methodesDeplacement;
    }

    // Récupération de toutes les méthodes nécessaires en fonction du name des annotations
    public Method[] getMethodesGraphisme(Method[] methods) {
        Method[] methodesGraphisme = new Method[1];
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if(annotation.toString().matches(".*.name=draw.*")) {
                    methodesGraphisme[0] = method;
                }
            }
        }
        return methodesGraphisme;
    }

    public void lancerPartie(JPanel panel, LinkedList<Robot> robots, int bordX, int bordY) {
        // Classes correspondantes aux plugins
        Object pluginAttaque = this.getClassesPlugins("attaque");
        Object pluginDeplacement = this.getClassesPlugins("deplacement");
        Object pluginGraphisme = this.getClassesPlugins("graphisme");

        // Methodes correspondantes aux plugins
        Method[] methodesAttaque = this.getMethodesAttque(pluginAttaque.getClass().getDeclaredMethods());
        Method[] methodesDeplacement = this.getMethodesDeplacement(pluginDeplacement.getClass().getDeclaredMethods());
        Method[] methodesGraphisme = this.getMethodesGraphisme(pluginGraphisme.getClass().getDeclaredMethods());

        this.afficherPluginsCharges(pluginAttaque, pluginDeplacement, pluginGraphisme);
        this.afficherRobotsPartie(robots);

        Object[] graphArgs = new Object[4];
        Object[] deplArgs = new Object[3];
        Object caracsAttaque = this.getCaracsAttaque(methodesAttaque[0], pluginAttaque) ;


        System.out.println("LA PARTIE COMMENCE" + System.lineSeparator());
        while(true) {
            panel.removeAll();
            this.usePluginGraph(robots, graphArgs, panel, methodesGraphisme[0], pluginGraphisme);
            
                this.tourDeJeu(robots, methodesDeplacement[0], pluginDeplacement, methodesAttaque[1], pluginAttaque, caracsAttaque, deplArgs, bordX, bordY);

                if(robots.size() == 1) {
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        this.annonceGagnant(panel, robots, bordX, bordY);
    }
    
    public void afficherPluginsCharges(Object pluginAttaque, Object pluginDeplacement, Object pluginGraphisme) {
        System.out.println(System.lineSeparator() + "*********************** PLUGINS ***********************");
        System.out.println("@Plugin d'attaque " + pluginAttaque.getClass().getName() + "chargé avec succès");
        System.out.println("@Plugin de déplacement " + pluginDeplacement.getClass().getName() + "chargé avec succès");
        System.out.println("@Plugin de graphisme " + pluginGraphisme.getClass().getName() + "chargé avec succès");
        System.out.println("*******************************************************" + System.lineSeparator());
    }
    
    public void afficherRobotsPartie(LinkedList<Robot> robots) {
        System.out.println("*********************** ROBOTS ************************");
        for(Robot r : robots) {
            System.out.println("Le robot " + r.getNom() + " rejoint la partie");
        }
        System.out.println("*******************************************************" + System.lineSeparator());
    }
    
    public Object[] getArgsGraph(Robot r, JPanel panel) {
        Object[] graphArgs = new Object[4];
        graphArgs[0] = r.getPosX();
        graphArgs[1] = r.getPosY();
        graphArgs[2] = r.getNom();
        graphArgs[3] = panel;
        return graphArgs;
    }
    
    public Object[] getArgsDepl(Robot r, LinkedList<Robot> robots) {
        Object[] deplArgs = new Object[3];
        deplArgs[0] = r.getPosX();
        deplArgs[1] = r.getPosY();
        deplArgs[2] = this.getPositions(robots, r);
        return deplArgs;
    }
    
    public void usePluginGraph(LinkedList<Robot> robots, Object[] graphArgs, JPanel panel, Method methodeGraphisme, Object pluginGraphisme) {
        for(Robot robot : robots) {
            graphArgs = this.getArgsGraph(robot, panel);
            try {
                methodeGraphisme.invoke(pluginGraphisme, graphArgs);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public Object getCaracsAttaque(Method methodeAttaque, Object pluginAttaque) {
        try {
            return methodeAttaque.invoke(pluginAttaque, (Object[]) null);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //Récupération des positions de tous les robots, sauf celui passé en paramètre
    public List<Point> getPositions(LinkedList<Robot> robots, Robot robotActuel) {
        List<Point> points = new LinkedList<Point>();
        for(Robot r : robots) {
            if (r != robotActuel) {
                points.add(new Point(r.getPosX(), r.getPosY()));
            }
        }
        return points;
    }
    
    public Object getNouvellesPos(Method methodeDeplacement, Object pluginDeplacement, Object[] deplArgs) {
        try {
            return methodeDeplacement.invoke(pluginDeplacement, deplArgs);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public void tourDeJeu(LinkedList<Robot> robots, Method methodeDeplacement, Object pluginDeplacement, Method methodeAttaque, Object pluginAttaque, Object caracsAttaque, Object[] deplArgs, int bordX, int bordY) {
        for(Robot robot : robots) {
            this.deplacementRobot(robots, robot, deplArgs, methodeDeplacement, pluginDeplacement, bordX, bordY);
            this.essaiAttaque(robots, robot, methodeAttaque, pluginAttaque, caracsAttaque);
        }
    }
    
    public void deplacementRobot(LinkedList<Robot> robots, Robot robot, Object[] deplArgs, Method methodeDeplacement, Object pluginDeplacement, int bordX, int bordY) {
            Object nouvellesPos;
            deplArgs = this.getArgsDepl(robot, robots);
        nouvellesPos = this.getNouvellesPos(methodeDeplacement, pluginDeplacement, deplArgs);
        
        robot.setPosX(((int[]) nouvellesPos)[0], bordX);
        deplArgs[0] = robot.getPosX();
        robot.setPosY(((int[]) nouvellesPos)[1], bordY);
        deplArgs[1] = robot.getPosY();
    }
    
    public void essaiAttaque(LinkedList<Robot> robots, Robot attaquant, Method methodeAttaque, Object pluginAttaque, Object caracsAttaque) {
            for(Robot r : robots) {
            try {
                if(!attaquant.equals(r) && attaquant.distance(r) <= (Integer)(methodeAttaque.invoke(pluginAttaque, (Object[]) null))){

                    r.degatsPris(((int[])caracsAttaque)[0]);
                    attaquant.setEnergie(attaquant.getEnergie() - (((int[])caracsAttaque)[1]));
                    System.out.println(attaquant.getNom() + " attaque " + r.getNom() + ", vie restante : " + r.getVie());
                    if(r.getVie() <= 0) {
                        robots.remove(r);
                    }
                }
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public void annonceGagnant(JPanel panel, LinkedList<Robot> robots, int bordX, int bordY) {
            JLabel winnerlabel = new JLabel(robots.get(0).getNom() + " est le vainqueur !");
        winnerlabel.setBounds((bordX/2)-200, (bordY/2)-200, 200, 200);
        winnerlabel.setOpaque(true);
        panel.add(winnerlabel);
        panel.revalidate();
        panel.repaint();
        System.out.println(robots.get(0).getNom() + " est le vainqueur !");
    }
}
