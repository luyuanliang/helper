package org.web.helper;

public class EnumHelper {

	public static enum BOOLEAN {
		Y, N
	}

	public enum DELETE {
		Y, N
	}
	
	public static boolean checkExist(Class<?> e, String arg) {
		Object[] array = e.getEnumConstants();
		for (int i = 0; i < array.length; i++) {
			if (array[i].toString().equalsIgnoreCase(arg)) {
				return true;
			}
		}
		return false;
	}


}
