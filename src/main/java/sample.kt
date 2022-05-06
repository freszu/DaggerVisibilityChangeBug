import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

@Component
interface A {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance string: String): A
    }

    fun inject(someActivity: SomeActivity)
}

data class StringConsumer @Inject constructor(
    // change visibility here to crash compilation private<->public
    private
    val string: String
)

class SomeActivity {
    @Inject
    lateinit var stringConsumer: StringConsumer
}

fun main() {
    val component = DaggerA.factory().create("hello")

    val activity = SomeActivity()

    component.inject(activity)

    println("trackerUser: ${activity.stringConsumer}")
}

