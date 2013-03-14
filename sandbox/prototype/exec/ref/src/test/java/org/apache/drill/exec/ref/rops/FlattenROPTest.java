package org.apache.drill.exec.ref.rops;

import org.apache.drill.common.expression.FieldReference;
import org.apache.drill.common.expression.SchemaPath;
import org.apache.drill.common.logical.data.Flatten;
import org.apache.drill.common.logical.data.Limit;
import org.apache.drill.exec.ref.IteratorRegistry;
import org.apache.drill.exec.ref.RecordIterator;
import org.apache.drill.exec.ref.TestUtils;
import org.apache.drill.exec.ref.eval.BasicEvaluatorFactory;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class FlattenROPTest {

  private FlattenROP buildROP(Flatten flatten) {
    FlattenROP flattenROP = new FlattenROP(flatten);
    flattenROP.setupEvals(new BasicEvaluatorFactory(new IteratorRegistry()));
    return flattenROP;
  }

  @Test
  public void flattenScalar() throws IOException {
    String input = "" +
        "{id: 1, name: \"jim\"}" +
        "{id: 2, name: \"bob\"}";
    RecordIterator incoming = TestUtils.jsonToRecordIterator("test", input);

    Flatten f = new Flatten(new FieldReference("flattened"), new SchemaPath("test.name"), false);
    FlattenROP flattenROP = buildROP(f);
    flattenROP.setInput(incoming);

    RecordIterator output = flattenROP.getOutput();
    System.out.println(TestUtils.recordIteratorToJSON(output));

  }

}
