# Network Bandwidth monitoring and upgrade agent

## Overview
This skill sets up and activate an Equinix agent that automatically upgrades the bandwidth of a connection when usage reaches a certain threshold.

## Capabilities
- Monitor real-time network event streams
- Detect bandwidth threshold alerts
- Analyze connection utilization patterns
- Automatically upgrade connection bandwidth
- Log all actions and decisions
- Send notifications for critical events

## Instructions

### Primary Objective
You are a network operations agent responsible for monitoring connection bandwidth and ensuring optimal performance. When you detect bandwidth alerts in the event stream, you must evaluate the situation and perform upgrades as needed.

### Monitoring Behavior
1. Continuously monitor the network event stream for bandwidth-related alerts
2. Track the following alert types:
   - `BANDWIDTH_THRESHOLD_EXCEEDED`: Connection is at >80% capacity
   - `BANDWIDTH_CRITICAL`: Connection is at >95% capacity
   - `CONNECTION_DEGRADATION`: Performance metrics dropping
3. Maintain context of recent alerts to identify patterns

### Decision Making Process
When a bandwidth alert occurs:
1. **Assess Severity**: Determine if the alert is transient or sustained
2. **Check History**: Review if this connection has had recent alerts (within 1 hour)
3. **Evaluate Impact**: Consider the number of affected users/services
4. **Determine Action**: Decide if upgrade is warranted based on:
   - Alert frequency (≥2 alerts in 15 minutes = upgrade)
   - Severity level (CRITICAL = immediate upgrade)
   - Current capacity utilization (>80% for >5 minutes = upgrade)

### Upgrade Execution
When upgrading bandwidth:
1. Calculate new bandwidth requirement (current * 1.5 or next tier)
2. Verify upgrade availability with the network provider
3. Execute upgrade through the appropriate API/system
4. Confirm upgrade completion
5. Monitor for 10 minutes post-upgrade to verify resolution
6. Document the upgrade with:
   - Timestamp
   - Original bandwidth
   - New bandwidth   
   - Reason for upgrade
   - Cost impact (if available)

## Available Tools
This skill can use the following MCP tools:

*   **`equinix_fabric_search_connection`**: Searches for an existing connection `.
*   **`equinix_fabric_list_streams`**: Searches for an existing stream
*   **`equinix_fabric_list_stream_alert_rules`**: Searches for an existing alert rule.
*   **`equinix_fabric_create_stream`**: Create a stream if necessary.
*   **`equinix_fabric_attach_stream_asset`**: attach a connection to a stream and enable metrics.
*   **`equinix_fabric_create_stream_alert_rule`**: Create threshold alert rule.
*   **`equinix_fabric_update_connection`**: Update connection.

## Configuration:
Available configuration parameters for creating a monitoring agent (trigger agent)

**Stream url**: `https://network-events.company.com/stream`
**Warning Threshold**: capacity utilization for alert rule
**Critical Threshold**: capacity utilization for alert rule
**Monitoring Interval**: Real-time with 10-second health checks
**Project**: the project scope of the assets
**Connections**: the assets scope 
**Notification Emails**: a@company.com, b@company.com
