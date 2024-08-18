package com.tfandkusu.ga913android.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class RuleProvider : RuleSetProvider {
    override val ruleSetId: String = "extra-rules"

    override fun instance(config: Config): RuleSet =
        RuleSet(
            id = ruleSetId,
            rules = listOf(SendScreenEvent(config)),
        )
}
