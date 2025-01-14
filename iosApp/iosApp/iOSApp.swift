import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        Main_iosKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
