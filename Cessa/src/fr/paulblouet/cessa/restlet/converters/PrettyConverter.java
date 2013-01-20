/*
 * Copyright 2012 Lionel Bécuwe, Paul Blouët, Fabien Lamarque, Frédéric Lucas
 *
 * This file is part of aca-cessa.
 *
 * aca-cessa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aca-cessa is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aca-cessa.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.paulblouet.cessa.restlet.converters;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.data.Preference;
import org.restlet.engine.converter.ConverterHelper;
import org.restlet.engine.resource.VariantInfo;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.representation.Variant;
import org.restlet.resource.UniformResource;

/**
 * Serialize an Object to a human readable output.
 * 
 * Currently supported:
 * <li>No deserialization<li>
 * <li>Root type must be non-primitive</li>
 * <li>Primitive fields</li>
 * <li>String fields</li>
 * <li>References to other compliant objects</li>
 * 
 * Next focus: deserialization
 * 
 * @author Paul Blouët
 *
 */
public class PrettyConverter extends ConverterHelper {

	private static final VariantInfo VARIANT_APPLICATION_ALL_XML = new VariantInfo(
			MediaType.APPLICATION_ALL_XML);

	private static final VariantInfo VARIANT_APPLICATION_XML = new VariantInfo(
			MediaType.APPLICATION_XML);

	private static final VariantInfo VARIANT_JSON = new VariantInfo(
			MediaType.APPLICATION_JSON);

	private static final VariantInfo VARIANT_TEXT_XML = new VariantInfo(
			MediaType.TEXT_XML);

	@Override
	public List<Class<?>> getObjectClasses(Variant source) {
		List<Class<?>> result = null;

		if (VARIANT_JSON.isCompatible(source)
				|| VARIANT_APPLICATION_ALL_XML.isCompatible(source)
				|| VARIANT_APPLICATION_XML.isCompatible(source)
				|| VARIANT_TEXT_XML.isCompatible(source)) {
			result = addObjectClass(result, Object.class);
		}

		return result;
	}

	@Override
	public List<VariantInfo> getVariants(Class<?> source) {
		List<VariantInfo> result = null;

		if (source != null) {
			result = addVariant(result, VARIANT_JSON);
			result = addVariant(result, VARIANT_APPLICATION_ALL_XML);
			result = addVariant(result, VARIANT_APPLICATION_XML);
			result = addVariant(result, VARIANT_TEXT_XML);
		}

		return result;
	}

	@Override
	public float score(Object source, Variant target, UniformResource resource) {
		float result = -1.0F;

		if (target == null) {
			result = 0.5F;
		} else if (VARIANT_JSON.isCompatible(target)) {
			result = 0.8F;
		} else if (VARIANT_APPLICATION_ALL_XML.isCompatible(target)
				|| VARIANT_APPLICATION_XML.isCompatible(target)
				|| VARIANT_TEXT_XML.isCompatible(target)) {
			result = 0.8F;
		} else {
			result = 0.5F;
		}

		return result;
	}

	@Override
	public <T> float score(Representation source, Class<T> target,
			UniformResource resource) {
		float result = -1.0F;

		if (target != null) {
			if (VARIANT_JSON.isCompatible(source)) {
				result = 0.8F;
			} else if (VARIANT_APPLICATION_ALL_XML.isCompatible(source)
					|| VARIANT_APPLICATION_XML.isCompatible(source)
					|| VARIANT_TEXT_XML.isCompatible(source)) {
				result = 0.8F;
			}
		} else {
			result = 0.5F;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T toObject(Representation source, Class<T> target,
			UniformResource resource) throws IOException {
		//TODO stub
		Object result = null;
		return (T) result;
	}

	@Override
	public Representation toRepresentation(Object source, Variant target,
			UniformResource resource) {
		// Create the buffer
		StringBuffer buffer = new StringBuffer();

		// Add the root type
		buffer.append(source.getClass().getName()).append('\n');

		// Call the recursive method
		write(buffer, source, "");

		// Return the representation
		Representation out = new StringRepresentation(buffer);
		return out;
	}

	@Override
	public <T> void updatePreferences(List<Preference<MediaType>> preferences,
			Class<T> entity) {
		updatePreferences(preferences, MediaType.APPLICATION_ALL_XML, 1.0F);
		updatePreferences(preferences, MediaType.APPLICATION_JSON, 1.0F);
		updatePreferences(preferences, MediaType.APPLICATION_XML, 1.0F);
		updatePreferences(preferences, MediaType.TEXT_XML, 1.0F);
	}

	private void write(StringBuffer buffer, Object source, String prefix) {
		// Represent each field
		// Value is either on line (primitive) or as a block (non-primitive)
		// Fields are accessed via their getter
		for (Method method : source.getClass().getDeclaredMethods()) {

			// Identify getters 
			if ((method.getName().length() > 3) 
					&& method.getName().startsWith("get")
					&& (method.getParameterTypes().length == 0)) {
				method.setAccessible(true);

				// Get return type and field name
				Class<?> fieldType = method.getReturnType();
				String fieldName = method.getName().substring(3, 4)
						.toLowerCase().concat(method.getName().substring(4));

				// Print them
				buffer.append(prefix).append(fieldType.getName()).append(' ');
				buffer.append(fieldName);

				// Exceptions are chained as RuntimeExceptions 
				try {
					if (fieldType.isPrimitive()) {
						// Print primitive type
						buffer.append(" = ").append(method.invoke(source));
					}
					else if (fieldType.equals(String.class)) {
						// Print escaped strings inside quotes
						String value = (String) method.invoke(source);
						value = value.replace("\"", "\\\"");
						buffer.append(" = \"").append(value).append('"');
					}
					else {
						// Try to recursively print other types
						Object value = method.invoke(source);
						if (value == null) {
							buffer.append(" = null");
						}
						else {
							buffer.append(":\n");
							write(buffer, value, prefix + "  ");
						}
					}
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				buffer.append('\n');
			}
		}
	}

}
