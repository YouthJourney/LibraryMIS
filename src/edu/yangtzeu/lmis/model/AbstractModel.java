package edu.yangtzeu.lmis.model;

import java.lang.reflect.Method;

public abstract class AbstractModel {

	public Object getFieldValue(Class<?> objectClass, String methodName) throws Exception{
		Method[] allMethods = objectClass.getDeclaredMethods();
		for(Method m : allMethods) {
			String mname = m.getName();
			if(mname.equals(methodName)) {
				return m.invoke(this, null);
				//return m.invoke(arg0, arg1);
			}
		}
		return null;
	}
}
