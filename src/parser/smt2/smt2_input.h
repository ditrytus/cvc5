/******************************************************************************
 * Top contributors (to current version):
 *   Christopher L. Conway, Aina Niemetz, Tim King
 *
 * This file is part of the cvc5 project.
 *
 * Copyright (c) 2009-2022 by the authors listed in the file AUTHORS
 * in the top-level source directory and their institutional affiliations.
 * All rights reserved.  See the file COPYING in the top-level source
 * directory for licensing information.
 * ****************************************************************************
 *
 * [[ Add one-line brief description here ]]
 *
 * [[ Add file-specific comments here ]]
 */

#include "cvc5parser_private.h"

#ifndef CVC5__PARSER__SMT2_INPUT_H
#define CVC5__PARSER__SMT2_INPUT_H

#include "parser/antlr_input.h"
#include "parser/smt2/Smt2Lexer.h"
#include "parser/smt2/Smt2Parser.h"

// extern void Smt2ParserSetAntlrParser(cvc5::parser::AntlrParser*
// newAntlrParser);

namespace cvc5 {

class Command;
class Expr;
class ExprManager;

namespace parser {

class Smt2;

class Smt2Input : public AntlrInput {
  typedef AntlrInput super;

  /** The ANTLR3 SMT2 lexer for the input. */
  pSmt2Lexer d_pSmt2Lexer;

  /** The ANTLR3 SMT2 parser for the input. */
  pSmt2Parser d_pSmt2Parser;

  /**
   * Initialize the class. Called from the constructors once the input
   * stream is initialized.
   */
  void init();

 public:
  /**
   * Create an input.
   *
   * @param inputStream the input stream to use
   */
  Smt2Input(AntlrInputStream& inputStream);

  /** Destructor. Frees the lexer and the parser. */
  virtual ~Smt2Input();

 protected:
  /**
   * Parse a command from the input. Returns <code>NULL</code> if
   * there is no command there to parse.
   *
   * @throws ParserException if an error is encountered during parsing.
   */
  Command* parseCommand() override;

  /**
   * Parse an expression from the input. Returns a null
   * <code>Expr</code> if there is no expression there to parse.
   *
   * @throws ParserException if an error is encountered during parsing.
   */
  cvc5::Term parseExpr() override;

};/* class Smt2Input */

}  // namespace parser
}  // namespace cvc5

#endif /* CVC5__PARSER__SMT2_INPUT_H */
