//
//  ContentView.swift
//  FlowSample
//
//  Created by Seyed Jafari on 18.03.21.
//

import SwiftUI
import app

struct ContentView: View {
    
    init() {
        print("ContentView init")
        let factory = FlowFactory()

        factory.composeNormalFlow { value in
                print("recieved from normal flow: \(value)")
        }
        
        factory.composeSharedFlow { value in
            print("recieved from shared flow: \(value)")
        }
        
        factory.sharedFlow.tryEmit(value: "1")
        
        
        factory.composeStateFlow { value in
            print("recieved from state flow: \(value)")
        }
        
        factory.stateFlow.tryEmit(value: "2")
        
    }
    
    var body: some View {
        Text("Hello, world!")
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
