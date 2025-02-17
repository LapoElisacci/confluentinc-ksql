/*
 * Copyright 2021 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.rest.server.execution;

import io.confluent.ksql.parser.tree.Statement;
import io.confluent.ksql.rest.entity.ErrorEntity;
import io.confluent.ksql.rest.entity.KsqlEntity;
import io.confluent.ksql.services.ConnectClient.ConnectResponse;
import io.confluent.ksql.statement.ConfiguredStatement;
import java.util.Optional;

public class DummyConnectServerErrors implements ConnectServerErrors {

  static final String FORBIDDEN_ERR = "FORBIDDEN";
  static final String UNAUTHORIZED_ERR = "UNAUTHORIZED";
  static final String DEFAULT_ERR = "DEFAULT";

  @Override
  public Optional<KsqlEntity> handleForbidden(
      final ConfiguredStatement<? extends Statement> statement,
      final ConnectResponse<?> response) {
    return Optional.of(new ErrorEntity(statement.getStatementText(), FORBIDDEN_ERR));
  }

  @Override
  public Optional<KsqlEntity> handleUnauthorized(
      final ConfiguredStatement<? extends Statement> statement,
      final ConnectResponse<?> response) {
    return Optional.of(new ErrorEntity(statement.getStatementText(), UNAUTHORIZED_ERR));
  }

  @Override
  public Optional<KsqlEntity> handleDefault(
      final ConfiguredStatement<? extends Statement> statement,
      final ConnectResponse<?> response) {
    return Optional.of(new ErrorEntity(statement.getStatementText(), DEFAULT_ERR));
  }
}
