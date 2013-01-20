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
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.data.Preference;
import org.restlet.engine.converter.ConverterHelper;
import org.restlet.engine.resource.VariantInfo;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.representation.Variant;
import org.restlet.resource.UniformResource;

import fr.paulblouet.cessa.restlet.model.ClassA;

public class DummyConverter extends ConverterHelper {

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
		ClassA result = new ClassA();
		result.setId(source.getText().split("\n")[0]);
		return (T) result;
	}

	@Override
	public Representation toRepresentation(Object source, Variant target,
			UniformResource resource) {
		ClassA asA = (ClassA) source;
		
		// Create the buffer
		StringBuffer buffer = new StringBuffer();
		buffer.append(source.getClass().getName()).append(asA.getId());

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
}
