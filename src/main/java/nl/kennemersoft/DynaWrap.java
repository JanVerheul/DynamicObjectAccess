package nl.kennemersoft;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DynaWrap<T> {

	private T obj;

	public DynaWrap(T obj) {
		this.obj = obj;
	}

	public Object get(String path) {
		String[] steps = path.split("\\.");
		Object result = this.obj;
		try {
			for (String step : steps) {
				Method method = result.getClass().getMethod(createGetterName(step));
				result = method.invoke(result);
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String discoverClassName() {
		return this.obj.getClass().getName();
	}

	public List<String> discoverProperties() {
		return discoverProperties(this.obj);
	}

	private List<String> discoverProperties(Object obj) {
		Method[] methods = obj.getClass().getDeclaredMethods();
		List<String> result = new ArrayList<>();
		for (Method method : methods) {
			if (method.getName().startsWith("get") && method.getParameters().length == 0) {
				String propName = createPropertyName(method.getName());
				result.add(propName);
				try {
					if (method.invoke(obj) instanceof Object) {
						Object subObj = method.invoke(obj);
						List<String> subProps = discoverProperties(subObj);
						for (String subProp : subProps) {
							result.add(propName + "." + subProp);
						}
					}
				} catch (Exception e) {
					// ignore
				}
			}
		}
		return result;
	}

	private String createGetterName(String name) {
		StringBuilder sb = new StringBuilder("get");
		sb.append(name.substring(0, 1).toUpperCase());
		sb.append(name.substring(1));
		return sb.toString();
	}

	private String createPropertyName(String name) {
		return name.substring(3, 4).toLowerCase() + name.substring(4);
	}

	// convenience methods to avoid casting
	public boolean getBoolean(String path) {
		return (Boolean) get(path);
	}

	public byte getByte(String path) {
		return (Byte) get(path);
	}

	public short getShort(String path) {
		return (Short) get(path);
	}

	public int getInt(String path) {
		return (Integer) get(path);
	}

	public long getLong(String path) {
		return (Long) get(path);
	}

	public float getFloat(String path) {
		return (Float) get(path);
	}

	public double getDouble(String path) {
		return (Double) get(path);
	}

	public String getString(String path) {
		return (String) get(path);
	}

}