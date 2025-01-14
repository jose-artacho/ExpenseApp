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
                .edgesIgnoringSafeArea(.all)
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
        }
    }
}
