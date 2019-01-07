#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>

@protocol PluginAdapterDelegate <NSObject>

- (UIView*) getView;
- (UIViewController*) getViewController;
- (void) fireEvent:(NSString*)obj event:(NSString*)eventName withData:(NSString*)jsonStr;
- (void) sendPluginResult:(CDVPluginResult*)result to:(NSString*)callbackId;

@end

@interface CDVPluginExt : CDVPlugin <PluginAdapterDelegate>

@property(nonatomic, retain) id<PluginAdapterDelegate> adapter;

@end

