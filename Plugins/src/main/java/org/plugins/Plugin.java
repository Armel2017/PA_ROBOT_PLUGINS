package org.plugins;

import java.lang.annotation.*;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.ANNOTATION_TYPE, ElementType.TYPE})
public @interface Plugin{
	String name();
	PluginType type();
}