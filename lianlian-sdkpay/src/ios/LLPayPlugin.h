#import "CDVPluginExt.h"
#import "LLPaySdk.h"

@interface LLPayPlugin : CDVPluginExt<LLPaySdkDelegate>

- (void) startPay:(CDVInvokedUrlCommand *)command;

@property (nonatomic, retain) LLPaySdk * sdk;

@end
