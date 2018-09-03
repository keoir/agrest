package io.agrest.runtime.encoder;

import io.agrest.ResourceEntity;
import io.agrest.encoder.Encoder;
import io.agrest.encoder.EncoderFilter;
import io.agrest.encoder.Encoders;
import io.agrest.encoder.PropertyMetadataEncoder;
import io.agrest.it.fixture.pojo.model.P1;
import io.agrest.it.fixture.pojo.model.P6;
import io.agrest.meta.DefaultLrAttribute;
import io.agrest.meta.LazyLrDataMap;
import io.agrest.meta.LrEntity;
import io.agrest.meta.LrEntityBuilder;
import io.agrest.meta.compiler.LrEntityCompiler;
import io.agrest.meta.compiler.PojoEntityCompiler;
import io.agrest.runtime.semantics.RelationshipMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class EncoderService_Pojo_Test {

	private static Collection<LrEntityCompiler> compilers;

	private EncoderService encoderService;
	private List<EncoderFilter> filters;

	@BeforeClass
	public static void setUpClass() {
		compilers = new ArrayList<>();
		compilers.add(new PojoEntityCompiler(Collections.emptyMap()));
	}

	@Before
	public void setUp() {

		this.filters = new ArrayList<>();

		IAttributeEncoderFactory attributeEncoderFactory = new AttributeEncoderFactoryProvider(Collections.emptyMap()).get();
		IStringConverterFactory stringConverterFactory = mock(IStringConverterFactory.class);

		this.encoderService = new EncoderService(this.filters, attributeEncoderFactory, stringConverterFactory,
				new RelationshipMapper(), Collections.<String, PropertyMetadataEncoder> emptyMap());
	}

	@Test
	public void testEncode_SimplePojo_noId() throws IOException {
		LrEntity<P1> p1lre = new LrEntityBuilder<>(P1.class, new LazyLrDataMap(compilers)).build();
		ResourceEntity<P1> descriptor = new ResourceEntity<P1>(p1lre);
		descriptor.getAttributes().put("name", new DefaultLrAttribute("name", String.class));

		P1 p1 = new P1();
		p1.setName("XYZ");
		assertEquals("{\"data\":[{\"name\":\"XYZ\"}],\"total\":1}", toJson(p1, descriptor));
	}

	@Test
	public void testEncode_SimplePojo_Id() throws IOException {

		P6 p6 = new P6();
		p6.setStringId("myid");
		p6.setIntProp(4);

		LrEntity<P6> p6lre = new LrEntityBuilder<>(P6.class, new LazyLrDataMap(compilers)).build();
		ResourceEntity<P6> descriptor = new ResourceEntity<P6>(p6lre);
		descriptor.getAttributes().put("intProp", new DefaultLrAttribute("intProp", Integer.class));
		descriptor.includeId();

		assertEquals("{\"data\":[{\"id\":\"myid\",\"intProp\":4}],\"total\":1}", toJson(p6, descriptor));
	}

	private String toJson(Object object, ResourceEntity<?> resourceEntity) {
        Encoder encoder = encoderService.dataEncoder(resourceEntity);
        return Encoders.toJson(encoder, Collections.singletonList(object));
    }
}