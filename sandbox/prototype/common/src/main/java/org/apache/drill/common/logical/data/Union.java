/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.apache.drill.common.logical.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("union")
public class Union extends LogicalOperatorBase {
  private final LogicalOperator[] inputs;
  private final boolean distinct;

  @JsonCreator
  public Union(@JsonProperty("inputs") LogicalOperator[] inputs){
    this(inputs, false);
  }
  
  @JsonCreator
  public Union(@JsonProperty("inputs") LogicalOperator[] inputs, @JsonProperty("distinct") Boolean distinct){
    this.inputs = inputs;
    for (LogicalOperator o : inputs) {
      o.registerAsSubscriber(this);
    }
    this.distinct = distinct;
  }

  public LogicalOperator[] getInputs() {
    return inputs;
  }

  public boolean isDistinct() {
    return distinct;
  }

  
  
}
