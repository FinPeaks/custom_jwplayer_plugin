import 'package:flutter/material.dart';
import 'package:jwplayer/configuration/jwplayer_configuration.dart';
import 'package:jwplayer/player_controller.dart';

class JWVideoPlayer extends StatefulWidget {
  /// Uses the given [controller] for all video rendered in this widget.
  const JWVideoPlayer({super.key, this.config, this.controller});

  // The configuration to start with, this can be set through a controller if we should start with none.
  final JWPlayerConfiguration? config;

  /// The [JWPlayerController] responsible for the content being played in
  /// this widget.
  final JWPlayerController? controller;

  @override
  State<JWVideoPlayer> createState() => _JWVideoPlayerState();

  static Future<String?> getPlatformVersion() async {
    return await JWPlayerController.getPlatformVersion();
  }

  static Future<void> setLicenseKey(String licenseKey) =>
      JWPlayerController.setLicenseKey(licenseKey);
}

class _JWVideoPlayerState extends State<JWVideoPlayer> {
  _JWVideoPlayerState() {
    _listener = () {
      final int newTextureId = _controller.textureId;
      if (newTextureId != _textureId) {
        setState(() {
          _textureId = newTextureId;
        });
      }
    };
    _onRemovedListener = (){

    };
  }

  late VoidCallback _listener;
  late VoidCallback _onRemovedListener;

  int? _textureId;

  late JWPlayerController _controller;

  @override
  void initState() {
    super.initState();
    _controller = widget.controller ?? JWPlayerController();
    setTextureId();
    _controller.addListener(_listener);
  }

  @override
  void didUpdateWidget(JWVideoPlayer oldWidget) {
    super.didUpdateWidget(oldWidget);
    _controller.removeListener(_onRemovedListener);
    _textureId = _controller.textureId;
    _controller.addListener(_listener);
  }

  @override
  void deactivate() {
    super.deactivate();
    _controller.removeListener(_onRemovedListener);
  }

  @override
  Widget build(BuildContext context) {
    return _textureId == null
        ? const Center(child: CircularProgressIndicator())
        : _controller.buildView(_textureId!, onViewBuilt);
  }

  Future<void> setTextureId() async {
    final id = await _controller.initialize();
    setState(() {
      _textureId = id;
    });
  }

  Future<void> onViewBuilt(int id) async {
    if (widget.config == null) return;
    await _controller.setConfig(widget.config!.toMap(), id);
  }

  @override
  void dispose(){
    _controller.stop();
    // _controller.removeListener(_onRemovedListener);
    _controller.dispose();
    super.dispose();
  }
}
