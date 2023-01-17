import * as path from 'path';
import { workspace, ExtensionContext, window } from 'vscode';

import {
	LanguageClient,
	LanguageClientOptions,
	RevealOutputChannelOn,
	ServerOptions,
	State
} from 'vscode-languageclient/node';

let client: LanguageClient;

export function activate(context: ExtensionContext) {
	const serverOptions: ServerOptions = {
		command: "klsp",
        options: {},
	};

	// Options to control the language client
	const clientOptions: LanguageClientOptions = {
		// Register the server for plain text documents
		documentSelector: [{ scheme: 'file', language: 'kframework' }],
		outputChannel: window.createOutputChannel("K framework"),
		revealOutputChannelOn: RevealOutputChannelOn.Never
	};

	// Create the language client and start the client.
	client = new LanguageClient(
		'k-vscode-lsclient',
		'K VSCode Language Client',
		serverOptions,
		clientOptions
	);
	console.log("LanguageClientPrint");
	console.log(client);

	client.onDidChangeState(
        (stateChangeEvent) => {
          if (stateChangeEvent.newState === State.Stopped) {
            window.showErrorMessage(
              "Failed to initialize the extension. Requires having `klsp` in your path."
            );
          }
        }
      );

	// Start the client. This will also launch the server
	client.start();
}

export function deactivate(): Thenable<void> | undefined {
	if (!client) {
		return undefined;
	}
	return client.stop();
}
