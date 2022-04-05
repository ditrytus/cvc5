/******************************************************************************
 * Top contributors (to current version):
 *   Aina Niemetz, Andrew Reynolds, Abdalrhman Mohamed, Mudathir Mohamed
 *
 * This file is part of the cvc5 project.
 *
 * Copyright (c) 2009-2021 by the authors listed in the file AUTHORS
 * in the top-level source directory and their institutional affiliations.
 * All rights reserved.  See the file COPYING in the top-level source
 * directory for licensing information.
 * ****************************************************************************
 *
 * The cvc5 java API.
 */

package io.github.cvc5;

/**
 * A cvc5 operator.
 *
 * An operator is a term that represents certain operators, instantiated
 * with its required parameters, e.g., a Term of kind
 * {@link Kind#BITVECTOR_EXTRACT}.
 */
public class Op extends AbstractPointer
{
  // region construction and destruction
  Op(Solver solver, long pointer)
  {
    super(solver, pointer);
  }

  protected native void deletePointer(long pointer);

  public long getPointer()
  {
    return pointer;
  }

  // endregion

  /**
   * Syntactic equality operator.
   * @api.note Both operators must belong to the same solver object.
   * @param t The operator to compare to for equality.
   * @return True If the operators are syntactically identical.
   */
  @Override
  public boolean equals(Object t)
  {
    if (this == t)
      return true;
    if (t == null || getClass() != t.getClass())
      return false;
    return equals(pointer, ((Op) t).getPointer());
  }

  private native boolean equals(long pointer1, long pointer2);

  /**
   * @return The Kind of this operator.
   */
  public Kind getKind()
  {
    try
    {
      int value = getKind(pointer);
      return Kind.fromInt(value);
    }
    catch (CVC5ApiException e)
    {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }

  private native int getKind(long pointer);

  /**
   * @return True If this operator is a null term.
   */
  public boolean isNull()
  {
    return isNull(pointer);
  }

  private native boolean isNull(long pointer);

  /**
   * @return True Iff this operator is indexed.
   */
  public boolean isIndexed()
  {
    return isIndexed(pointer);
  }

  private native boolean isIndexed(long pointer);

  /**
   * @return The Number of indices of this op.
   */
  public int getNumIndices()
  {
    return getNumIndices(pointer);
  }

  private native int getNumIndices(long pointer);

  /**
   * Get the index at position {@code i}.
   * @param i The position of the index to return.
   * @return The Index at position i.
   */
  public Term get(int i) throws CVC5ApiException
  {
    Utils.validateUnsigned(i, "index");
    long termPointer = get(pointer, i);
    return new Term(solver, termPointer);
  }

  private native long get(long pointer, int i);

  /**
   * @return A String representation of this operator.
   */
  protected native String toString(long pointer);
}
