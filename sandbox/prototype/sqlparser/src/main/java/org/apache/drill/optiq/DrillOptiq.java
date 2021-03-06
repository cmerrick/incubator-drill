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
package org.apache.drill.optiq;

import org.eigenbase.relopt.Convention;
import org.eigenbase.relopt.RelOptPlanner;

/**
 * @author jhyde
 */
public class DrillOptiq {
  /** Calling convention for relational expressions that are "implemented" by
   * generating Drill logical plans. */
  public static final Convention CONVENTION = new Convention.Impl("DRILL", DrillRel.class);

  static void registerStandardPlannerRules(RelOptPlanner planner) {
    planner.addRule(EnumerableDrillRule.ARRAY_INSTANCE);
    planner.addRule(EnumerableDrillRule.CUSTOM_INSTANCE);

//    planner.addRule( CascadingTableModificationConverterRule.INSTANCE );
//    planner.addRule( CascadingAggregateConverterRule.INSTANCE );
//    planner.addRule( CascadingCalcConverterRule.INSTANCE );

//    planner.addRule( CascadingFilterRule.INSTANCE );
//    planner.addRule( CascadingProjectRule.INSTANCE );
//    planner.addRule( CascadingSortRule.INSTANCE );
//    planner.addRule( CascadingJoinRule.INSTANCE );
//    planner.addRule( CascadingUnionRule.INSTANCE );
//    planner.addRule( AbstractConverter.ExpandConversionRule.instance );
  }
}
