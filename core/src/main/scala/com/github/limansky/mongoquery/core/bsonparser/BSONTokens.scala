/*
 * Copyright 2014 Mike Limansky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.limansky.mongoquery.core.bsonparser

import com.github.limansky.mongoquery.core.BSON.IdentPart

import scala.util.parsing.combinator.token.StdTokens

trait BSONTokens extends StdTokens {

  case class DoubleLit(chars: String) extends Token

  case class OperatorLit(chars: String) extends Token

  case class RegexLit(expression: String, options: String) extends Token {
    override val chars = s"/$expression/$options"
  }

  case object Variable extends Token {
    override val chars = "variable"
  }

  case class FieldLit(parts: List[IdentPart]) extends Token {
    override def chars: String = parts.map(_.name) mkString "."
    override def toString: String = chars
  }
}
