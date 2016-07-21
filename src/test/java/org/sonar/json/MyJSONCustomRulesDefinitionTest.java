/*
 * SonarQube JSON Custom Rules Plugin Example
 * Copyright (C) 2016-2016 David RACODON
 * david.racodon@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.json;

import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Rule;
import org.sonar.json.checks.ForbiddenKeysCheck;

import static org.fest.assertions.Assertions.assertThat;

public class MyJSONCustomRulesDefinitionTest {

  @Test
  public void test() {
    MyJSONCustomRulesDefinition rulesDefinition = new MyJSONCustomRulesDefinition();

    RulesDefinition.Context context = new RulesDefinition.Context();
    rulesDefinition.define(context);

    RulesDefinition.Repository repository = context.repository("custom-json");

    assertThat(repository.name()).isEqualTo("My JSON Custom Repository");
    assertThat(repository.language()).isEqualTo("json");
    assertThat(repository.rules()).hasSize(2);

    RulesDefinition.Rule forbiddenKeysRule = repository.rule(ForbiddenKeysCheck.class.getAnnotation(Rule.class).key());
    assertThat(forbiddenKeysRule).isNotNull();
    assertThat(forbiddenKeysRule.name()).isEqualTo(ForbiddenKeysCheck.class.getAnnotation(Rule.class).name());
  }

}
