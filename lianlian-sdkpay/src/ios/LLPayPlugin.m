
#import <CommonCrypto/CommonDigest.h>
#import "LLPayPlugin.h"

static LLPayType payType = LLPayTypeVerify;

@implementation LLPayPlugin

- (void)pluginInitialize
{
    [super pluginInitialize];
    self.sdk = [[LLPaySdk alloc] init];
    self.sdk.sdkDelegate = self;
}

- (NSString*) __getProductShortName {
    return @"LLPay";
}

- (void) startPay:(CDVInvokedUrlCommand *)command {
    NSLog(@"startPay");

    NSDictionary *traderInfo = [command argumentAtIndex:0 withDefault:[NSNull null]];
    [self.sdk presentLLPaySDKInViewController:[self getViewController]
                                      withPayType:payType
                                 andTraderInfo:traderInfo];

    [self sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK] to:command.callbackId];
}


- (void)paymentEnd:(LLPayResult)resultCode withResultDic:(NSDictionary*)dic {

    NSError *err;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dic options:0 error:&err];
    NSString *ret = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    [self fireEvent:[self __getProductShortName]
              event:@"onLLPayEnd"
           withData:[NSString stringWithFormat:@"{\"ret\":%@}", ret]];
}


@end

