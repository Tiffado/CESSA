package util;

import java.util.ArrayList;
import java.util.Collection;

public class Factory {
	public static <T> Collection<T> newCollection() {
		return new ArrayList<T>();
	}

}
