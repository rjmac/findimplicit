package com.rojoma.findimplicit

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context

object FindImplicit {
  def impl[T](ctx: Context)(implicit t: ctx.WeakTypeTag[T]): ctx.Expr[T] = {
    import ctx.mirror._
    import ctx.universe._
    ctx.echo(ctx.enclosingPosition, ctx.inferImplicitValue(t.tpe).toString())
    ctx.Expr[T](q"_root_.scala.Predef.implicitly[$t]")
  }

  def apply[T]: T = macro impl[T]
}
