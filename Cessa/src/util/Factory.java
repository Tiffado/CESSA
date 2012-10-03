package util;

import java.util.ArrayList;
import java.util.Collection;

public class Factory {
	public static <T> Collection<T> createCollection() {
		return new ArrayList<T>();
	}

}
