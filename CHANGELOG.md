## 3.0.2
### Fixed
- Event Manager accepting ``null`` events

## 3.0.1
### Added
- Added ability to toggle the event bus post method
- Added flatten method to IValue
- Added an EventState to GuiDisplayEvent
### Changed
- Mixins are now only applied client-side
- Made MotionUpdateEvent cancellable
- Made UpdateEvent cancellable
### Fixed
- CommandHandler opening chat when not in a world
- Fixed left click events not firing
- Fixed CDK ClientAPI dependency not deobfuscating srg names
- Fixed CDK version having quotes by default
- Fixed CDK example code export
### Removed
- Removed ProfilerEvent
