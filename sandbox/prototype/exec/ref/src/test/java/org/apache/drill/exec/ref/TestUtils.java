package org.apache.drill.exec.ref;

import org.apache.drill.common.config.DrillConfig;
import org.apache.drill.common.expression.SchemaPath;
import org.apache.drill.exec.ref.rops.DataWriter;
import org.apache.drill.exec.ref.rse.JSONRecordReader;
import org.apache.drill.exec.ref.rse.OutputStreamWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestUtils {
  public static RecordIterator jsonToRecordIterator(String schemaPath, String j) throws IOException {
    InputStream is = new ByteArrayInputStream(j.getBytes());
    JSONRecordReader reader = new JSONRecordReader(new SchemaPath(schemaPath), DrillConfig.create(), is, null);
    return reader.getIterator();
  }

  public static String recordIteratorToJSON(RecordIterator iterator) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    OutputStreamWriter writer = new OutputStreamWriter(output, DataWriter.ConverterType.JSON, true);
    writer.setup();

    RecordIterator.NextOutcome next;
    while((next = iterator.next()) != RecordIterator.NextOutcome.NONE_LEFT) {
      writer.recordRecord(iterator.getRecordPointer());
    }
    writer.finish(RunOutcome.OutcomeType.SUCCESS);
    return output.toString();
  }

  public static int getIteratorCount(RecordIterator out) {
    RecordIterator.NextOutcome next;
    int counter = 0;
    while((next = out.next()) != RecordIterator.NextOutcome.NONE_LEFT) {
      counter++;
      //RecordPointer rp = out.getRecordPointer();
      //System.out.println(rp);
    }
    return counter;
  }
}
